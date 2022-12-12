import {authRequest} from "@/utils/request";
const CLIENT_ID = 'Basic YWRtaW46YWRtaW4xMjM0NTY='
export function login(data) {

  return authRequest.post({
    url:'/oauth/token',
    headers: {
      'Authorization': CLIENT_ID
    },
    params: Object.assign({ 'grant_type': 'password' }, data)
  })
}



export function logout() {
  return authRequest.delete({
    url: '/oauth/logout'
  })
}
