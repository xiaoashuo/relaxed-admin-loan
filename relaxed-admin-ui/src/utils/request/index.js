import YiRequest from './request'
import { API_BASE_URL } from './config'
import store from '@/store'
import { MessageBox, Notification, Message } from 'element-ui'

const authRequest = new YiRequest({
  baseURL: API_BASE_URL,
  interceptors: {
    requestInterceptor: (config) => {
      const token = store.getters.token

      if (token) {
        // @ts-ignore
        config.headers.Authorization = 'Bearer ' + token
      }
      return config
    },
    requestInterceptorCatch: (error) => {
      return Promise.reject(error)
    },
    responseInterceptor: (res) => {
      return res.data
    },
    responseInterceptorCatch: (error) => {
      console.log('err', error,error.code) // for debug
      let errorMessage = ((error.response || {}).data || {}).message || '请求出现错误，请稍后再试'
      Message({
        message: errorMessage,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(error)
    }
  }
})

//axios 实列
const yiRequest = new YiRequest({
  baseURL: API_BASE_URL,
  interceptors: {
    requestInterceptor: (config) => {
      const token = store.getters.token
      if (token) {
        // @ts-ignore
        config.headers.Authorization = 'Bearer ' + token
      }
      //TODO  get post请求映射params参数 post 最好改写成formData，以data形式传参
      if (
        (config.method === 'post' || config.method === 'get') &&
        config.params
      ) {
        let url = config.url + '?'
        for (const propName of Object.keys(config.params)) {
          const value = config.params[propName]
          var part = encodeURIComponent(propName) + '='
          if (value !== null && typeof value !== 'undefined') {
            if (typeof value === 'object') {
              for (const key of Object.keys(value)) {
                if (value[key] !== null && typeof value[key] !== 'undefined') {
                  let params = propName + '[' + key + ']'
                  let subPart = encodeURIComponent(params) + '='
                  url += subPart + encodeURIComponent(value[key]) + '&'
                }
              }
            } else {
              url += part + encodeURIComponent(value) + '&'
            }
          }
        }
        url = url.slice(0, -1)
        config.params = {}
        config.url = url
      }
      return config
    },
    requestInterceptorCatch: (error) => {
      return Promise.reject(error)
    },
    responseInterceptor: (res) => {
      const headers = res.headers
      if (
        !(
          headers != null &&
          headers['content-type'] &&
          headers['content-type'].startsWith('application/json')
        )
      ) {
        return res
      }
      const data = res.data
      if (data.code !== 200) {
        Message({
          message: data.message || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
        return Promise.reject(new Error(data.message || 'Error'))
      }
      return data
    },
    responseInterceptorCatch: (error) => {
      console.log('err', error) // for debug
      handlerResponseError(error)
      return Promise.reject(error)
    }
  }
})

function handlerResponseError(error){
  if (error.response) {
    console.log(error.response)
    const data = error.response.data
    if (error.response.status === 403) {
      Notification.error({
        title: 'Forbidden',
        message: data.message,
        duration: 5 * 1000
      })
    }
    if (error.response.status === 400) {
      Notification.error({
        title: '参数错误',
        message: data.message,
        duration: 5 * 1000
      })
    }
    if (error.response.status === 401) {
      //
      Notification.error({
        title: 'Unauthorized',
        message: 'Authorization verification failed',
        duration: 5 * 1000
      })
      store.dispatch('auth/logout')
    }
    if (error.response.status === 500) {
      Notification.error({
        title: 'Error',
        message: data.message,
        duration: 1 * 1000
      })
    }
  }
}
export { authRequest }
export default yiRequest
