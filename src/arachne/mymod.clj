(ns arachne.mymod
  (:require [arachne.core.util :as util]
            [arachne.mymod.config :as http-cfg]
            [arachne.mymod.schema :as schema]
            [arachne.mymod.validators :as v]))

(defprotocol Handler
  "A logical Ring-style request handler which is also an Arachne component that
  can have dependencies, etc."
  (handle [this request] "Given a Ring-style request map, return a Ring-style
  response")

(defn schema
  "Return the schema for the arachne.http module"
  []
  schema/schema)

(defn configure
  "Configure the arachne.http module"
  [cfg]
  (v/add-validators cfg))
