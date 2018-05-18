(ns app.state
  (:require [reagent.core :refer [atom]]))

(defonce app-state
  (atom
   {:hello "world"
    :haikus [{:verse "The wren\nEarns his living\nNoiselessly."
              :author "Kobayashi Issa"}]
    :haiku-index 0
    :position 0
    :state :default
    :start-time nil}))