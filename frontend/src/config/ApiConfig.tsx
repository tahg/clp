import axios from 'axios'

export const URL: string = 'http://localhost:8080'

const API = axios.create({
  baseURL: URL,
  headers: {
    'Content-type': 'application/json'
  }
})

API.interceptors.request.use(
  function (request) {
    if (request !== undefined && request.headers !== undefined) {
      let token = sessionStorage.getItem('token')
      if (token !== null) request.headers['Authorization'] = token
    }
    return request
  },
  (error) => {
    return Promise.reject(error)
  }
)

export default API