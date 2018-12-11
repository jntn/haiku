(ns app.events
  (:require [app.state :refer [app-state haiku-verse last-char? last-haiku?]]))

(defn handle-keypress [key]
  (let [pos (:position @app-state)
        correct (= (nth (haiku-verse) pos) key)]
    (when (and correct (= 0 (:position @app-state)))
      (swap! app-state assoc-in [:start-time] (.getTime (js/Date.))))

    (if correct
      (swap! app-state update-in [:position] inc)
      (swap! app-state assoc-in [:state] :error))

    (when (and correct (= :error (:state @app-state)))
      (swap! app-state assoc-in [:state] :default))

    (when (and correct (last-char?))
      (swap! app-state assoc-in [:state] :showing-result))))

(defn handle-result [key]
  (when (= " " key) ((swap! app-state assoc-in [:state] :default)
                     (swap! app-state assoc-in [:position] 0)
                     (if (last-haiku?)
                       (swap! app-state assoc-in [:haiku-index] 0)
                       (swap! app-state update-in [:haiku-index] inc)))))

(defn user-typed-key [key]
  (case (:state @app-state)
    :default (handle-keypress key)
    :error (handle-keypress key)
    :showing-result (handle-result key)))
