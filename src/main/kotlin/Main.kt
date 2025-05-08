import kotlinx.coroutines.*
import java.io.File
import java.io.OutputStreamWriter
import java.io.PrintStream
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import kotlin.random.Random

data class LrcLine(val timeMs: Long, val text: String)

fun parseLrc(lines: List<String>): List<LrcLine> {
    val pattern = Pattern.compile("\\[(\\d{2}):(\\d{2})\\.(\\d{2})\\](.*)")
    return lines.mapNotNull { line ->
        val matcher = pattern.matcher(line)
        if (matcher.matches()) {
            val min = matcher.group(1).toLong()
            val sec = matcher.group(2).toLong()
            val centi = matcher.group(3).toLong()
            val text = matcher.group(4).trim()
            val totalMs = (min * 60 + sec) * 1000 + centi * 10
            LrcLine(totalMs, text)
        } else null
    }.sortedBy { it.timeMs }
}

suspend fun typeWriter(text: String, delayPerChar: Long = 70) {
    for (char in text) {
        print(char)
        delay(Random.nextLong(60, 90))
    }
    println()
}

fun playWavAndGetClip(filePath: String): Clip? {
    return try {
        val audioInput = AudioSystem.getAudioInputStream(File(filePath))
        val clip = AudioSystem.getClip()
        clip.open(audioInput)
        clip.start()
        clip
    } catch (e: Exception) {
        println("Error playing audio: ${e.message}")
        null
    }
}

fun main() = runBlocking {
    System.setOut(PrintStream(System.out, true, StandardCharsets.UTF_8.name()))
    val lrcText = """
        [00:08.00] این حس جدیدو دوس داره دلم
        [00:10.00] حالش بده انگار بیماره دلم
        [00:12.00] عاشق شده باز بیکاره دلم ...
        [00:16.00] پیش یکی دیگه باز گیره دلم
        [00:17.30] با من مثل هر روز درگیره دلم
        [00:19.00] آرامش من زد زیر دلم ...
        [00:22.00] دل من سر به راه نمیشه عاشقه همیشه
        [00:24.30] میگم آخه بسه میگه آخریشه
        [00:26.35] دل من با خودم غریبس باز میمونه بی کس
        [00:29.00] خسته میشم از بس بی حواسه سادس
    """.trimIndent()

    val lyrics = parseLrc(lrcText.lines())

    val clip = playWavAndGetClip("song.wav")

    var prevTime = 0L
    for (line in lyrics) {
        val delayTime = line.timeMs - prevTime
        delay(delayTime)
        typeWriter(line.text)
        prevTime = line.timeMs
    }

    if (clip != null) {
        while (clip.isRunning) {
            delay(200)
        }
        clip.close()
    }
}
