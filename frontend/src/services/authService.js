import { axiosInstance } from './axiosService';

function writeToken(token) {
  window.localStorage.setItem('token', token)
}

export function getToken() {
  return window.localStorage.getItem('token')
}

export function isUserLoggedIn() {
  return Boolean(getToken())
}

export function logout() {
  window.localStorage.removeItem('token');
}

export async function login(username, password, successCallback, errorCallback) {
  axiosInstance().post('/api/auth/login', { username, password })
  .then(res => {
    console.log(res)
    writeToken(res.data.accessToken)
    successCallback()
  })
  .catch(err => {
    console.log(err)
    logout()
    errorCallback()
  })
}
