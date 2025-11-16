import { API_BASE_URL } from '@/config/api';
import axios from 'axios';

const api = axios.create({
    baseURL: `${API_BASE_URL}/api`,
    headers: { 'Content-Type': 'application/json' },
    timeout: 10000,
});

export default api;
