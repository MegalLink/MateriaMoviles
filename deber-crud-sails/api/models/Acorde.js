/**
 * Acorde.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    notacion_latina:{
      type:'string',
      required:true,
    },
    notacion_inglesa:{
      type:'string',
      required:true,
    },
    img_url:{
      type:'string',
      required:true,
    },
    latitud:{
      type:'number',
      required:true,
    },
    longitud:{
      type:'number',
      required:true,
    },
    url_redireccion:{
      type:'string',
      required:true,
    }


    
       


  },

};

