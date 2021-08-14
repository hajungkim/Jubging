import axios from 'axios';

export const HTTP = axios.create({
  baseURL: `http://i5b207.p.ssafy.io/api`,
  //baseURL: `http://localhost:8080/`,
  headers: {
    
  }
})