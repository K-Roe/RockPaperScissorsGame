import * as SecureStore from "expo-secure-store";
import { createContext, useContext, useEffect, useRef, useState } from "react";
import api from "../../lib/axios";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Prevent boot() from running more than once
  const booted = useRef(false);

  // LOGIN
  async function login(email, password) {
    const { data } = await api.post("/mobile/login", { email, password });

    await SecureStore.setItemAsync("token", data.token);
    await SecureStore.setItemAsync("user", JSON.stringify(data.user));

    api.defaults.headers.common.Authorization = `Bearer ${data.token}`;
    setUser(data.user);
  }

  // LOGOUT
  async function logout() {
    await SecureStore.deleteItemAsync("token");
    await SecureStore.deleteItemAsync("user");
    delete api.defaults.headers.common.Authorization;

    setUser(null);
  }

  // BOOT — runs ONLY once (no loops)
  async function boot() {
    if (booted.current) return; // prevent multiple calls
    booted.current = true;

    console.log("Boot: clearing previous session");

    await SecureStore.deleteItemAsync("token");
    await SecureStore.deleteItemAsync("user");
    setUser(null);

    setLoading(false);
  }

  useEffect(() => {
    boot();
  }, []);

  return (
    <AuthContext.Provider value={{ user, loading, login, logout }}>
      {!loading && children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
