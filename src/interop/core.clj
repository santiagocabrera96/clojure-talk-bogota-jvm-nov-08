(ns interop.core
  (:require [clj-java-decompiler.core :refer [decompile]]))

(.requestUserAttention (. java.awt.Taskbar getTaskbar) true false)

(.setIconImage
  (java.awt.Taskbar/getTaskbar)
  (.createImage
    (java.awt.Toolkit/getDefaultToolkit)
    (java.net.URL. "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSp-7WmGrclnPy4Y7vQMYkdkORBUeQcmyVigw&s")))

(javax.swing.JOptionPane/showMessageDialog nil "Hello, world")

; Podemos ver a qué compila el código de Clojure
(decompile #(javax.swing.JOptionPane/showMessageDialog nil "Hello, world"))

; Sacado del libro de Programming Clojure
;---
; Excerpted from "Programming Clojure, Third Edition",
; published by The Pragmatic Bookshelf.
; Copyrights apply to this code. It may not be used to create training material,
; courses, books, articles, and the like. Contact us if you are in doubt.
; We make no guarantees that this code is fit for any purpose.
; Visit http://www.pragmaticprogrammer.com/titles/shcloj3 for more book information.
;---
(import '[javax.sound.midi MidiSystem])
(defprotocol MidiNote
  (to-msec [this tempo])
  (key-number [this])
  (play [this tempo midi-channel]))

(defn perform [notes & {:keys [tempo] :or {tempo 88}}]
  (with-open [synth (doto (MidiSystem/getSynthesizer).open)]
    (let [channel (aget (.getChannels synth) 0)]
      (doseq [note notes]
        (play note tempo channel)))))

(defrecord Note [pitch octave duration]
  MidiNote
  (to-msec [this tempo]
    (let [duration-to-bpm {1 240, 1/2 120, 1/4 60, 1/8 30, 1/16 15}]
      (* 1000 (/ (duration-to-bpm (:duration this))
                 tempo))))
  (key-number [this]
    (let [scale {:C 0,  :C# 1, :Db 1,  :D 2,
                 :D# 3, :Eb 3, :E 4,   :F 5,
                 :F# 6, :Gb 6, :G 7,   :G# 8,
                 :Ab 8, :A 9,  :A# 10, :Bb 10,
                 :B 11}]
      (+ (* 12 (inc (:octave this)))
         (scale (:pitch this)))))
  (play [this tempo midi-channel]
    (let [velocity (or (:velocity this) 64)]
      (.noteOn midi-channel (key-number this) velocity)
      (Thread/sleep (int (to-msec this tempo))))))

(comment
  (def close-encounters [(->Note :D 3 1/2)
                         (->Note :E 3 1/2)
                         (->Note :C 3 1/2)
                         (->Note :C 2 1/2)
                         (->Note :G 2 1/2)])
  (future (perform close-encounters))

  (def jaws (for [duration [1/2 1/2 1/4 1/4 1/8 1/8 1/8 1/8]
                  pitch [:E :F]]
              (Note. pitch 2 duration)))
  (perform jaws)

  (perform (map #(update-in % [:octave] inc) close-encounters))
  (perform (map #(update-in % [:octave] identity) close-encounters))
  (perform (map #(update-in % [:octave] dec) close-encounters))
  )
