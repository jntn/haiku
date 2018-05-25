(ns app.events
  (:require [app.state :refer [app-state haiku-verse]]))

(defn user-typed-key [key]
  (let [pos (:position @app-state)
        correct (= (nth (haiku-verse) pos) key)]
    (if correct
      (swap! app-state update-in [:position] inc)
      (swap! app-state assoc-in [:state] :error))

    (when (and correct (= :error (:state @app-state)))
      (swap! app-state assoc-in [:state] :default))))
