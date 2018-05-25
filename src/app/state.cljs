(ns app.state
  (:require [reagent.core :refer [atom track]]))

(defonce app-state
  (atom
   {:haikus [{:verse "The wren\rEarns his living\rNoiselessly."
              :author "Kobayashi Issa"}]
    :haiku-index 0
    :position 0
    :state :default
    :start-time nil}))

(defn haiku []
  (nth (:haikus @app-state) (:haiku-index @app-state)))

(defn haiku-verse []
  (:verse (haiku)))

(defn haiku-author []
  (:author (haiku)))
