// app/login.tsx
import { router } from "expo-router";
import { useState } from "react";
import { Alert, StyleSheet, Text, TextInput, TouchableOpacity, View } from "react-native";
import { useAuth } from "../src/context/AuthContext";

export default function Login() {
  const { login, loading } = useAuth(); 
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [submitting, setSubmitting] = useState(false);

  async function handleLogin() {
    if (!email || !password) {
      Alert.alert("Error", "Please enter both email and password.");
      return;
    }

    setSubmitting(true);

    try {
      await login(email, password);

      // Redirect after successful login
      router.replace("/tavern");

    } catch (err: any) {
      console.log("❌ Login error:", err.response?.data || err.message);

      Alert.alert(
        "Login Failed",
        err.response?.data?.message ?? "Invalid credentials or server unreachable."
      );
    } finally {
      setSubmitting(false);
    }
  }

  if (loading || submitting) return <Text>Loading...</Text>;

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Login</Text>

      <TextInput
        placeholder="Email"
        value={email}
        autoCapitalize="none"
        onChangeText={setEmail}
        style={styles.input}
      />

      <TextInput
        placeholder="Password"
        secureTextEntry
        value={password}
        onChangeText={setPassword}
        style={styles.input}
      />

      <TouchableOpacity style={styles.button} onPress={handleLogin}>
        <Text style={styles.buttonText}>Log In</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: "center", padding: 20 },
  title: { fontSize: 30, textAlign: "center", marginBottom: 20 },
  input: {
    borderWidth: 1,
    padding: 12,
    borderRadius: 10,
    marginBottom: 10,
    borderColor: "#ccc",
  },
  button: {
    backgroundColor: "#8e24aa",
    padding: 15,
    borderRadius: 10,
  },
  buttonText: { color: "white", textAlign: "center", fontSize: 18 },
});
