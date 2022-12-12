import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}
export function set(key,value) {
  return Cookies.set(key, JSON.stringify(value))
}
export function get(key) {
  let value=Cookies.get(key)
  if (value){
    return  JSON.parse(value)
  }
}
export function remove(key) {
  return Cookies.remove(key)
}
export function removeToken() {
  return Cookies.remove(TokenKey)
}

