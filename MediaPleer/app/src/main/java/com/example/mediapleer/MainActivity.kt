package com.example.mediapleer

import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.SeekBar
import com.example.mediapleer.databinding.ActivityMainBinding
import java.util.*
import android.media.MediaPlayer as MediaPlayer1


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    lateinit var MediaPlayer: MediaPlayer1// создаем переменную для медиаплеера
    lateinit var Seec: SeekBar//создаем переменную для сик бара

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MediaPlayer = android.media.MediaPlayer.create(this, R.raw.glukofon)// связываем медиаплеер с файлом

        Seec = binding.seekBar//свзяываем переменную с разметкой
        Seec.setMax(MediaPlayer.duration)// связываем переменную с длительностью звука
        Seec.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {//создаем слушатель на сикбар
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
             if(p2){// если двигает  ползунок юзер
                 MediaPlayer.seekTo(p1)//тогда идет перемотка
             }


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })









        Timer().scheduleAtFixedRate(object : TimerTask() {// создаем счетчик для передвижения ползунка
            override fun run() {
                Seec.setProgress(MediaPlayer.currentPosition)// связываем звук с позицие позунка
            }
        }, 0, 1000)// выставляем отсчет


        binding.play.setOnClickListener {
            if (MediaPlayer.isPlaying) {
                MediaPlayer.pause()
                binding.playIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            } else {
                binding.playIcon.setImageResource(R.drawable.ic_baseline_pause_24)
                MediaPlayer.start()
            }
            binding.back.setOnClickListener {
                binding.playIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                MediaPlayer.pause()
               Seec.setMax(0)
            }
            binding.next.setOnClickListener {
                binding.playIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                MediaPlayer.pause()
                Seec.setMax(1000)
            }
        }
    }
}











