(ns app.state
  (:require [reagent.core :refer [atom track]]))

(defonce app-state
  (atom
   {:haikus [{:verse "The wren\rEarns his living\rNoiselessly." :author "Kobayashi Issa"}
             {:verse "brilliant moon\ris it true that you too\rmust pass in a hurry?" :author "Kobayashi Issa"}
             {:verse "Blowing from the west\rFallen leaves gather\rIn the east." :author "Yosa Buson"}
             {:verse "Over the wintry\rforest, winds howl in rage\rwith no leaves to blow." :author "Natsume Soseki"}
             {:verse "old pond --\ranother lily\rin bloom" :author "Inoue Hiromi"}
             {:verse "after the storm\rthe wind chimes\rrealigning" :author "Asopa Sanjuktaa"}
             {:verse "dawn --\rthe maple's colors\rspread through the fog" :author "Cindy Zackowitz"}]
    :haiku-index 0
    :position 0
    :state :default
    :start-time 0
    :end-time 0}))

(defn haiku []
  (nth (:haikus @app-state) (:haiku-index @app-state)))

(defn haiku-verse []
  (:verse (haiku)))

(defn haiku-author []
  (:author (haiku)))

(defn wpm []
  "WPM calculation according to https://www.speedtypingonline.com/typing-equations"
  (let [minutes (/ (- (:end-time @app-state) (:start-time @app-state)) 60000)
        typed-entries (count (haiku-verse))
        words (/ typed-entries 5)]
    (js/Math.round (/ words minutes))))

(defn last-char? []
  (= (count (haiku-verse)) (:position @app-state)))

(defn last-haiku? []
  (print (count (:haikus @app-state)))
  (print (:haiku-index @app-state))
  (= (count (:haikus @app-state)) (+ 1 (:haiku-index @app-state))))

(defn error-state? []
  (= :error (:state @app-state)))