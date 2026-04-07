import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', //porta Spring Boot
});

//intecepta e adiciona o token JWT em toda requisição
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
