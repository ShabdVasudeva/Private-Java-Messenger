# 💬 Private Messaging CLI System (Java)

![Java](https://img.shields.io/badge/Java-17%2B-blue.svg?style=flat&logo=java)
![Status](https://img.shields.io/badge/Status-Working-green.svg)
![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)

A modern command-line based private messaging system built in **Java** using `Socket` programming.  
This app allows users on the **same network** or **over the internet** to send **private 1-on-1 messages** in real-time.

---

## ✨ Features

- ✅ Command-line chat interface
- ✅ Private user-to-user messages (`@username message`)
- ✅ Multi-client support using threads
- ✅ LAN and Internet support (via IP or Ngrok)
- ✅ Simple, clean, and hackable codebase

---

## 📁 Project Structure

```
📁 PvtMessaging/
├── Server.java # Java server to handle clients & relay messages
├── Client.java # Java client to connect, send & receive messages
└── README.md # This file
```

---

## 🚀 Getting Started

### 🧩 Requirements

- Java 17+ installed
- LAN / Wi-Fi network (for testing locally)
- Optional: [Ngrok](https://ngrok.com/) for internet access

---

### 🖥️ Start the Server

```bash
javac Server.java
java Server

Output:
Server started on port 12345Server started on port 12345

javac Client.java
java Client

Output:
Enter your username:
{user_name}
```
