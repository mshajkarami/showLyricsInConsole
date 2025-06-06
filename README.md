﻿# showLyricsInConsole
# 🎵 showLyricsInConsole

A Kotlin console application that plays a `.wav` audio file and displays synced lyrics from an embedded `.lrc`-formatted string — with a typewriter-style animation.  
This project is great for learning:

- ✅ Kotlin coroutines (`suspend`, `delay`)
- ✅ Audio playback with Java Sound API (`Clip`, `AudioSystem`)
- ✅ Regex-based parsing of subtitle formats
- ✅ Handling UTF-8 and Persian text in the terminal
- ✅ Real-time animation and synchronization logic

---

## 🚀 Features

- ⏱️ Parses `.lrc` lyrics with millisecond accuracy
- 🎹 Plays `.wav` audio files from local storage
- 🧠 Displays each line with a typewriter animation effect
- 🔤 Full support for Persian (UTF-8) text in terminal
- 🧵 Uses coroutines to control delay and playback timing

---

## 🛠️ How It Works

1. **Parse LRC** — The lyrics are written as an embedded multiline string. Each line has a timestamp (`[mm:ss.SS]`) and the lyric.
2. **Play Audio** — A `.wav` file is played using Java's `Clip` API.
3. **Sync Display** — As the audio plays, the code calculates when to display each line and types it out character by character.

---

## 📦 Dependencies

This project uses only standard libraries:

- Kotlin standard library
- `kotlinx.coroutines`
- Java Sound API (included in the JDK)

---

## ▶️ Usage

1. Place your audio file in the project directory and name it:

   ```text
   song.wav
   
Insert your .lrc lyrics directly in the lrcText variable as a multiline string.

Build and run the project using Kotlin CLI or in an IDE like IntelliJ:

💻 Using Kotlin Command Line

kotlinc ShowLyrics.kt -include-runtime -d ShowLyrics.jar
java -jar ShowLyrics.jar

Or with a .kts script:
kotlinc -script ShowLyrics.kts

🧪 Using Gradle
Add dependencies for kotlinx-coroutines-core.

Run with your favorite Gradle task.

📝 Example LRC Input 
[00:08.00] این حس جدیدو دوس داره دلم
[00:10.00] حالش بده انگار بیماره دلم
[00:12.00] عاشق شده باز بیکاره دلم ...
📚 License
This project is open-source and free to use under the MIT License.

🙋 Author
Developed with ❤️ by Mohamad Saleh Haj Karami
GitHub: mshajkarami
