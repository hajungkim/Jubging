import axios from 'axios';

export const HTTP = axios.create({
  // baseURL: `https://i5b207.p.ssafy.io/api`,  
  baseURL: `http://localhost:8080/`,
  headers: {
    
  }
})