(ns jntn.api.haikus
  (:require [clojure.spec.alpha :as s]))

(def haikus
  [{:verse "The wren\rEarns his living\rNoiselessly." :author "Kobayashi Issa"}
   {:verse "brilliant moon\ris it true that you too\rmust pass in a hurry?" :author "Kobayashi Issa"}
   {:verse "Blowing from the west\rFallen leaves gather\rIn the east." :author "Yosa Buson"}
   {:verse "Over the wintry\rforest, winds howl in rage\rwith no leaves to blow." :author "Natsume Soseki"}
   {:verse "old pond --\ranother lily\rin bloom" :author "Inoue Hiromi"}
   {:verse "after the storm\rthe wind chimes\rrealigning" :author "Asopa Sanjuktaa"}
   {:verse "dawn --\rthe maple's colors\rspread through the fog" :author "Cindy Zackowitz"}
   {:verse "sleepless night -\rin the distance\rmidnight express" :author "Swann Gail"}
   {:verse "A lull in the storm --\rthe woodpecker darts\rto another tree." :author "Penelope Greenwell"}
   {:verse "summer butterfly\ra girl wearing a white dress\rin the morning breeze" :author "Kayo"}
   {:verse "bamboo flute\rthe scent\rof green tea" :author "Grace Mathew"}
   {:verse "cold starry night -\rfrom the forest edge the sound\rof animal kill" :author "Visnja McMaster"}])

(s/def ::verse string?)
(s/def ::author string?)
(s/def ::haiku (s/keys :req-un [::verse ::author]))
(s/def ::haikus (s/coll-of ::haiku))

(defn get-random-haiku []
  (rand-nth haikus))

(defn handler [req res]
  (.end res (.stringify js/JSON (clj->js (get-random-haiku)))))