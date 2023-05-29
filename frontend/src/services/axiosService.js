import axios from 'axios';
import { getToken, logout } from './authService';

export function axiosInstance({ requiresAuth = false } = {}) {
  const options = {};

  //? Decide add token or not
  if (requiresAuth) {
    options.headers = {
      Authorization: `Bearer ${getToken()}`
    }
  }

  const instance = axios.create(options)
  instance.interceptors.response.use(response => {
    return response
  }, error => {
    if (requiresAuth && error.response.status === 401) {
      logout()
      window.location.href = '/login'
    }
    return Promise.reject(error)
  });

  return instance
}