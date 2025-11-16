// app/tavern.tsx
import { router } from "expo-router";
import { ImageBackground, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { useAuth } from "../src/context/AuthContext";

export default function TavernScreen() {
  const { user, logout } = useAuth();

  if (!user) router.replace("/");

  return (
    <ImageBackground
      source={require("../assets/stone-wall.jpg")}
      style={styles.bg}
      resizeMode="cover"
    >
      <View style={styles.overlay} />

      <View style={styles.container}>
        <Text style={styles.title}>🍺 The Adventurer’s Tavern</Text>
        <Text style={styles.subtitle}>Welcome, {user.name}</Text>

        <TouchableOpacity
          style={styles.card}
          onPress={() => alert("Join a Campaign")}
        >
          <Text style={styles.cardTitle}>⚔️ Join Existing Campaign</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.card}
          onPress={() => alert("Host a New Adventure")}
        >
          <Text style={styles.cardTitle}>🏕️ Host New Adventure</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.card}
          onPress={() => alert("Quest Log")}
        >
          <Text style={styles.cardTitle}>📜 Quest Log</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.logoutButton} onPress={logout}>
          <Text style={styles.logoutText}>Logout</Text>
        </TouchableOpacity>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  bg: { flex: 1 },
  overlay: {
    ...StyleSheet.absoluteFillObject,
    backgroundColor: "rgba(0,0,0,0.5)",
  },
  container: {
    flex: 1,
    padding: 20,
    alignItems: "center",
  },
  title: {
    fontSize: 34,
    color: "#ffd27f",
    marginTop: 60,
    marginBottom: 10,
  },
  subtitle: {
    fontSize: 20,
    color: "#fff",
    marginBottom: 30,
  },
  card: {
    width: "90%",
    backgroundColor: "#f5deb3",
    padding: 20,
    borderRadius: 16,
    marginBottom: 15,
  },
  cardTitle: {
    fontSize: 20,
    color: "#5a3e1b",
    textAlign: "center",
  },
  logoutButton: {
    marginTop: 40,
    backgroundColor: "#8e24aa",
    padding: 12,
    width: "70%",
    borderRadius: 10,
  },
  logoutText: { color: "white", textAlign: "center", fontSize: 18 },
});
