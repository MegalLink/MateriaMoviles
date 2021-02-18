/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  //validaciones
  //https://sailsjs.com/documentation/concepts/models-and-orm/validations#validation-rules

tableName:'epn_usuario',//nombre de la tabla
  attributes: {
    cedula:{ //nombre atributo
      type:'string',
      required:true,
      columnName:'usr_cedula',//nobre de la columna para una bd
      unique:true, 
      minLength:10,
      maxLength:25
    },
    nombre:{
      type:'string',
      required:true,
      minLength:3
    },
    correo:{
      type:'string',
      isEmail:true,
      required:true
 
    },
    estadoCivil:{
      type:'string',
      isIn:['Soltero','Casado','Divorciado','Viudo','Union libre'], //Con is In solo podemos llenar estas opciones
      defaultsTo:'Soltero'//por defecto
    },
    password:{
      type:'string',
      regex:/^[a-zA-Z]\w{3,14}$/
    },
    pokemons:{ //one to many en plural
      collection:'pokemon', //referncia al modelo
      via:'entrenador' //nombre FK
    }

  },

};

