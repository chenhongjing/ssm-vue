import request from './request'

export const getPieChartData = () => {
  return request({
    url: '/pie_chart',
    method: 'get'
  })
}
