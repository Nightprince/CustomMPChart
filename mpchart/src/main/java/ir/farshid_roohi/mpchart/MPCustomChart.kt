package ir.farshid_roohi.mpchart

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.custom_chart.view.*
import java.util.*

/**
 * Created by Farshid Roohi.
 * MpChartCustom | Copyrights 12/12/18.
 */

class MPCustomChart : LinearLayout {

    private var dataOne: LineData? = null
    private var dataSecond: LineData? = null

    private var title = ""
    private var titleDataSetFirst = ""
    private var titleDataSetSecond = ""
    private var typeface: Typeface? = null
    private var legendOne: Legend? = null
    private var legendTwo: Legend? = null

    private val listDataSetFirst = ArrayList<Entry>()
    private val listDataSetSecond = ArrayList<Entry>()


    constructor(context: Context) : super(context) {
        this.initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.initialize()

    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        this.initialize()
    }

    private fun initialize() {
        LayoutInflater.from(context).inflate(R.layout.custom_chart, this, true)

        chartOne.xAxis.isEnabled = false
        chartOne.description.isEnabled = false
        chartOne.setTouchEnabled(false)

        chartSecond.xAxis.isEnabled = false
        chartSecond.description.isEnabled = false
        chartSecond.setTouchEnabled(false)

        chartOne.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_chart_color))
        chartOne.animateX(2500)

        this.chartOne.axisLeft.isEnabled = false
        this.chartOne.axisRight.isEnabled = false
        this.chartOne.xAxis.setDrawGridLines(true)

        this.dataOne = LineData()
        this.chartOne.data = this.dataOne

        legendOne = this.chartOne.legend
        legendOne?.form = Legend.LegendForm.CIRCLE
        legendOne?.textSize = 12f
        legendOne?.textColor = Color.WHITE
        legendOne?.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legendOne?.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legendOne?.orientation = Legend.LegendOrientation.HORIZONTAL
        legendOne?.setDrawInside(false)

        // config chart two
        this.chartSecond.xAxis.isEnabled = false
        this.chartSecond.description.isEnabled = false
        this.chartSecond.setTouchEnabled(false)

        this.chartSecond.setBackgroundColor(Color.TRANSPARENT)
        this.chartSecond.animateX(2500)

        this.chartSecond.axisLeft.isEnabled = false
        this.chartSecond.axisRight.isEnabled = false
        this.chartSecond.xAxis.setDrawGridLines(true)


        this.dataSecond = LineData()
        this.chartSecond.data = this.dataSecond

        legendTwo = this.chartSecond.legend
        legendTwo?.form = Legend.LegendForm.CIRCLE
        legendTwo?.textSize = 12f
        legendTwo?.textColor = Color.WHITE
        legendTwo?.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legendTwo?.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legendTwo?.orientation = Legend.LegendOrientation.HORIZONTAL
        legendTwo?.setDrawInside(false)

    }

    fun setTitle(title: String) {
        this.title = title
        this.txtTitle.text = title
    }

    fun setTitleDataSetFirst(titleDataSetFirst: String) {
        this.titleDataSetFirst = titleDataSetFirst
    }

    fun setTitleDataSetSecond(titleDataSetSecond: String) {
        this.titleDataSetSecond = titleDataSetSecond
        this.notifyChart()
    }

    private fun notifyChart() {
        this.chartOne.data.notifyDataChanged()
        this.chartOne.notifyDataSetChanged()

        this.chartSecond.data.notifyDataChanged()
        this.chartSecond.notifyDataSetChanged()
    }

    fun setListDataSetFirst(list: ArrayList<ir.farshid_roohi.mpchart.model.Entry>) {
        this.listDataSetFirst.clear()

        list.forEach {
            this.listDataSetFirst.add(Entry(it.x!!, it.y!!))
        }

        if (this.dataOne!!.dataSets.isEmpty()) {
            this.dataOne!!.addDataSet(
                createLineDataSet(
                    this.listDataSetFirst,
                    ContextCompat.getColor(context, R.color.chart_yellow), titleDataSetFirst
                )
            )
        }

        this.chartOne.data.notifyDataChanged()
        this.chartOne.notifyDataSetChanged()
        this.chartOne.invalidate()
    }

    fun setListDataSetSecond(list: ArrayList<ir.farshid_roohi.mpchart.model.Entry>) {
        this.listDataSetSecond.clear()
        list.forEach {
            this.listDataSetSecond.add(Entry(it.x!!, it.y!!))
        }
        if (this.dataSecond!!.dataSets.isEmpty()) {
            this.dataSecond!!.addDataSet(
                createLineDataSet(
                    this.listDataSetSecond,
                    ContextCompat.getColor(context, android.R.color.white), titleDataSetSecond
                )
            )
        }

        this.chartSecond.data.notifyDataChanged()
        this.chartSecond.notifyDataSetChanged()
        this.chartSecond.invalidate()
    }

    private fun createLineDataSet(entries: ArrayList<Entry>, @ColorInt color: Int, title: String): LineDataSet {
        val lineDataSet = LineDataSet(entries, title)
        lineDataSet.color = color
        lineDataSet.setCircleColor(color)
        lineDataSet.fillColor = color
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.circleRadius = 4f
        lineDataSet.lineWidth = 2f
        lineDataSet.fillAlpha = 90
        lineDataSet.mode = LineDataSet.Mode.LINEAR
        lineDataSet.setDrawFilled(true)
        return lineDataSet
    }

    public fun setTypeface(typeface: Typeface) {
        this.typeface = typeface

        legendOne?.let {
            it.typeface = typeface
        }
        legendTwo?.let {
            it.typeface = typeface
        }
    }
}