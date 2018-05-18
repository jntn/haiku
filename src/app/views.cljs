(ns app.views
  (:require [app.state :refer [app-state]]))

(defn letter [c i]
  (let [position (:position app-state)]
    (println c, i)
    (if (= c \newline)
      [:br {:class "hej"}]
      [:span c])))

(defn haiku []
  (let [haiku-char-list (-> (:haikus @app-state)
                            (get (:position @app-state))
                            (:verse)
                            (seq))]
    [:div (map-indexed (fn [i c]
                         ^{:key i} [letter c i])
                       haiku-char-list)]))

(get [1 2 3] (:pos ()))