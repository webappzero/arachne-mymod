(ns arachne.tmpl.schema
  (:require [arachne.core.config :refer [tempid]]
            [arachne.core.config.ontology :as o]))

(def schema
  (concat
    (o/class :arachne.tmpl/Thing [:arachne/Component]
      "An abstract arachne thing"
      (o/attr :arachne.tmpl.thing/color :blue
        "The color of the thing"))))
