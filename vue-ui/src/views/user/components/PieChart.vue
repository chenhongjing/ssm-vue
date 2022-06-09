<template>
  <div :class="className" :style="{ height: height, width: width }" />
</template>

<script>
import { getPieChartData } from '@/api/pie_chart'
import resize from '@/views/user/components/mixins/resize'
import * as echarts from 'echarts'

import 'echarts/theme/macarons.js'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null,
      data: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeUnmount() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    async initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      var pieChartData = []
      var pieChartDataName = []
      const res = await getPieChartData()

      console.log(res)
      pieChartData = res
      pieChartData.forEach((element) => {
        pieChartDataName.push(element.name)
      })

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: pieChartDataName
        },
        series: [
          {
            name: '记录数',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '38%'],
            data: pieChartData,
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    },
    initPieChartData() {
      getPieChartData().then((res) => {
        this.data = res
      })
    }
  }
}
</script>
