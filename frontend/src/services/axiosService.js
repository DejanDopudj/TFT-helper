import axios from 'axios';

export function axiosInstance({ requiresAuth = false } = {}) {
  const options = {};

  //? Decide add token or not
  if (requiresAuth) {
    options.headers.Authorization = 'JWT TOKEN'
  }

  const instance = axios.create(options);
  instance.interceptors.response.use(response => {
    return response;
  }, error => {
    return Promise.reject(error);
  });

  return instance;
}