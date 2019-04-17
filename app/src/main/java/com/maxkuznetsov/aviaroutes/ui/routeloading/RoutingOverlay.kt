package com.maxkuznetsov.aviaroutes.ui.routeloading

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.maxkuznetsov.aviaroutes.R


/**
 * Created by max
 */

private const val CURRENT_PROGRESS_STATE = "current_point_state"
private const val SAVED_STATE = "super_state"
private const val ANIMATION_TIME = 10_000

class RoutingOverlay(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {

    private val routePath = Path()

    private val routeMatrix = Matrix()

    private var currentProgress = 0f

    private var rect = Rect()
    private val planeDrawable = ContextCompat.getDrawable(context, R.drawable.ic_plane)

    private val routePaint = Paint().apply {
        color = 0xff429AE2.toInt()
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    fun setPoints(from: Point, to: Point) {
        val fromX = from.x.toDouble()
        val fromY = from.y.toDouble()
        val toX = to.x.toDouble()
        val toY = to.y.toDouble()

        val distanceBetweenPoints = Math.sqrt(
            Math.pow(Math.abs(toX - fromX), 2.0)
                    + Math.pow(Math.abs(toY - fromY), 2.0)
        )
        val radius = distanceBetweenPoints.toFloat() / 2

        val midX = (fromX + toX).toFloat() / 2
        val midY = (fromY + toY).toFloat() / 2

        val slope = if (fromX > toX || fromY > toY) {
            Math.abs(toY - fromY) / (toX - fromX)
        } else {
            Math.abs(fromY - toY) / (fromX - toX)
        }
        val angle = Math.atan(slope)
        val cos = Math.cos(angle).toFloat()
        val sin = Math.sin(angle).toFloat()

        val midXFrom = Math.abs(fromX + midX).toFloat() / 2
        val midYFrom = Math.abs(fromY + midY).toFloat() / 2
        val arcXFrom = midXFrom - radius * sin
        val arcYFrom = midYFrom - radius * cos

        val midXTo = Math.abs(midX + toX.toFloat()) / 2
        val midYTo = Math.abs(midY + toY.toFloat()) / 2
        val arcXTo = midXTo + radius * sin
        val arcYTo = midYTo + radius * cos

        routePath.moveTo(fromX.toFloat(), fromY.toFloat())
        routePath.cubicTo(fromX.toFloat(), fromY.toFloat(), arcXFrom, arcYFrom, midX, midY)
        routePath.cubicTo(midX, midY, arcXTo, arcYTo, toX.toFloat(), toY.toFloat())

        planeDrawable?.intrinsicHeight
        val pathMeasure = PathMeasure(routePath, false)
        val planeHeight = planeDrawable?.intrinsicHeight ?: 0
        val planeWidth = planeDrawable?.intrinsicWidth ?: 0
        rect = Rect(-(planeWidth / 2), -(planeHeight / 2), planeWidth / 2, planeHeight / 2)

        val animator = ValueAnimator.ofFloat(currentProgress * pathMeasure.length, pathMeasure.length)
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            currentProgress =  value / pathMeasure.length
            pathMeasure.getMatrix(
                value, routeMatrix,
                PathMeasure.POSITION_MATRIX_FLAG
                        + PathMeasure.TANGENT_MATRIX_FLAG
            )
            invalidate()
        }
        animator.duration = (ANIMATION_TIME * (1 - currentProgress)).toLong()
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(routePath, routePaint)
        canvas?.matrix = routeMatrix
        planeDrawable?.bounds = rect
        canvas?.let { planeDrawable?.draw(it) }
    }

    override fun onSaveInstanceState(): Parcelable? {
        return Bundle().apply {
            putParcelable(SAVED_STATE, super.onSaveInstanceState())
            putFloat(CURRENT_PROGRESS_STATE, currentProgress)
        }
    }

    override fun onRestoreInstanceState(parcelable: Parcelable) {
        var state = parcelable
        if (state is Bundle) {
            val bundle = state
            currentProgress = bundle.getFloat(CURRENT_PROGRESS_STATE)
            state = bundle.getParcelable(SAVED_STATE) as Parcelable
        }
        super.onRestoreInstanceState(state)
    }
}