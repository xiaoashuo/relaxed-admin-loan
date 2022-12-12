import axios from 'axios'
import { Loading } from 'element-ui';


class YiRequest{
  instance
  interceptors
  showLoading
  loading
  constructor(config) {
    this.instance=axios.create(config)
    this.interceptors=config.interceptors
    this.showLoading=config.showLoading??false
    //从config取出的拦截器是对应的实列拦截器
    this.instance.interceptors.request.use(this.interceptors?.requestInterceptor,this.interceptors?.requestInterceptorCatch)
    this.instance.interceptors.response.use(this.interceptors?.responseInterceptor,this.interceptors?.responseInterceptorCatch)
    //添加所有的实列都有的拦截器
    this.instance.interceptors.request.use(config=>{
      if (this.showLoading){
        this.loading= Loading.service({
          lock: true,
          text: '正在请求数据...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
      }
      return config
    },error=>{
      return Promise.reject(error)
    })
    this.instance.interceptors.response.use(res=>{
      this.loading?.close();
      return res;
    },error => {
      this.loading?.close();
      return Promise.reject(error)
    })

  }

  request(config){
    return new Promise((resolve, reject) => {
      if (config.interceptors?.requestInterceptor){
        config=config.interceptors.requestInterceptor(config)
      }


      this.showLoading=config.showLoading

      this.instance.request(config).then(res=>{
        if (config.interceptors?.responseInterceptor){
          res=config.interceptors.responseInterceptor(res)
        }
        //将结果返回出去
        resolve(res)
      }).catch(err=>{
        reject(err)
      })
    })
  }

  get(config){
    return this.request({...config,method:'GET'})
  }
  post(config){
    return this.request({...config,method:'POST'})
  }
  put(config){
    return this.request({...config,method:'PUT'})
  }
  delete(config){
    return this.request({...config,method:'DELETE'})
  }
  patch(config){
    return this.request({...config,method:'PATCH'})
  }
}

export  default YiRequest
