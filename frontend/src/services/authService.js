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

export async function register(email, username, password, successCallback, errorCallback) {
  axiosInstance().post('/api/auth/register', { email, username, password })
  .then(res => {
    console.log(res)
    successCallback()
  })
  .catch(err => {
    console.log(err)
    if (err.response?.data?.message) errorCallback(err.response.data.message)
    else errorCallback(null)
  })
}

export async function whoami(successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).get('/api/auth/whoami')
  .then(res => {
    successCallback(res.data)
  })
  .catch(err => {
    console.log(err)
    if (err.response?.data?.message) errorCallback(err.response.data.message)
    else errorCallback(null)
  })
}
