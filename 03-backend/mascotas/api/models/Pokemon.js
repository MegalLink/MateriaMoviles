/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

const Usuario = require("./Usuario");

module.exports = {

  attributes: {
    nombre:{
      type:'string'
    },
    entrenador:{ //to one el mismo nombre de la relacion via
      model:'usuario',
      required:false//para 1 a n sino es de 0 a n
    },
    batalla:{
      model:'batalla',
      required:true
    }
  
  },

};

