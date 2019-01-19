(ns jntn.app.state
  (:require [reagent.core :refer [atom track]]))

(defonce app-state
  (atom
   {:haiku nil
    :position 0
    :state :default
    :start-time 0
    :end-time 0}))

(defn haiku []
  (:haiku @app-state))

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

(defn error-state? []
  (= :error (:state @app-state)))