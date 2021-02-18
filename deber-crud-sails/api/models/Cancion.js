/**
 * Cancion.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

  //var nombre:String, var autor:String,var genero:String,var acordes:String
  nombre:{
    type:'string',
    required:true,
  },
  autor:{
    type:'string',
  },
  
  genero:{
    type:'string',

  },
  acordes:{
    type:'string'
  }


  },

};

