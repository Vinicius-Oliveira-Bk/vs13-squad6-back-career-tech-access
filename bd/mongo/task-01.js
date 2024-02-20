//--------------INSERTS--------------

Atlas atlas-uupjfq-shard-0 [primary] test> use vs_13_equipe_6;
switched to db vs_13_equipe_6

Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.createCollection("usuario");
{ ok: 1 }

Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.usuario.insertOne({ "nome": "Jorginho", "dataNascimento": new Date("2000-01-01"), "cpf": "032213654598", "email": "jorginho@dominio.com", "senha": "jorginho123", "ehPcd": "N", "contatos": [{ "descricao": "Bom dia", "telefone": "4002-8922", "tipo": "COMERCIAL" }], "enderecos": [{ "logradouro": "Rua Cinza", "numero": 9, "complemento": "apto 2", "cep": "13254-321", "cidade": "Pirapozinho", "estado": "Sao Paulo", "pais": "Brasil", "tipo": "RESIDENCIAL" }, { "logradouro": "Rua Azul", "numero": 15, "cep": "32165-215", "cidade": "Osasco", "estado": "Sao Paulo", "pais": "Brasil", "tipo": "COMERCIAL" }, { "logradouro": "Rua Verde", "numero": 17, "cep": "56897-512", "cidade": "Sao José do Rio Preto", "estado": "Sao Paulo", "pais": "Brasil", "tipo": "COMERCIAL" }] });
{
  acknowledged: true,
  insertedId: ObjectId("65d34aa199597fc500bac7d8")
}



Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.usuario.insertOne({ "nome": "Mateus", "dataNascimento": new Date("2001-01-01"), "cpf": "032213654597", "email": "mateuzinho@dominio.com", "senha": "mateuzinho123", "ehPcd": "S", "contatos": [{ "descricao": "Boa noite", "telefone": "3221-1245", "tipo": "RESIDENCIAL" }, { "descricao": "Boa tarde", "telefone": "3222-2223", "tipo": "RESIDENCIAL" }], "enderecos": [], "tipoDeficiencia": "manco", "certificadoDeficie
ncia": "http://certificado.com", "imagemDocumento": "http://image.jpg" });
{
  acknowledged: true,
  insertedId: ObjectId("65d34ac199597fc500bac7d9")
}



Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.usuario.insertOne({ "nome": "Miguel", "dataNascimento": new Date("1980-01-01"), "cpf": "32233222123", "email": "miguelzinho@dominio.com", "senha": "miguelzinho123", "ehPcd": "S", "contatos": [{ "descricao": "Casa do Miguel", "telefone": "6656-5565", "tipo": "RESIDENCIAL" }], "enderecos": [{ "logradouro": "Rua Marrom", "numero": 9, "complemento": "Casa", "cep": "35689-987", "cidade": "Maringá", "estado": "Paraná", "pais": "Brasil", "tipo": "RESIDENCIAL" }], "tipoDeficiencia": "TDAH", "certificadoDeficiencia": "http://certificado.com", "imagemDocumento": "http://image.jpg" });
{
  acknowledged: true,
  insertedId: ObjectId("65d34ad599597fc500bac7da")
}



Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.usuario.insertOne({ "nome": "Carlos", "dataNascimento": new Date("2005-01-01"), "cpf": "032213654597
", "email": "carlinhos@dominio.com", "senha": "carlinhos123", "ehPcd": "N" });
{
  acknowledged: true,
  insertedId: ObjectId("65d34ae599597fc500bac7db")
}



//--------------FINDS--------------
//--------------FIND BY EHPCD = "S"--------------
Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.usuario.find({"ehPcd": {$eq: "S"}});
[
  {
    _id: ObjectId("65d34ac199597fc500bac7d9"),
    nome: 'Mateus',
    dataNascimento: ISODate("2001-01-01T00:00:00.000Z"),
    cpf: '032213654597',
    email: 'mateuzinho@dominio.com',
    senha: 'mateuzinho123',
    ehPcd: 'S',
    contatos: [
      {
        descricao: 'Boa noite',
        telefone: '3221-1245',
        tipo: 'RESIDENCIAL'
      },
      {
        descricao: 'Boa tarde',
        telefone: '3222-2223',
        tipo: 'RESIDENCIAL'
      }
    ],
    enderecos: [],
    tipoDeficiencia: 'manco',
    certificadoDeficiencia: 'http://certificado.com',
    imagemDocumento: 'http://image.jpg'
  },
  {
    _id: ObjectId("65d34ad599597fc500bac7da"),
    nome: 'Miguel',
    dataNascimento: ISODate("1980-01-01T00:00:00.000Z"),
    cpf: '32233222123',
    email: 'miguelzinho@dominio.com',
    senha: 'miguelzinho123',
    ehPcd: 'S',
    contatos: [
      {
        descricao: 'Casa do Miguel',
        telefone: '6656-5565',
        tipo: 'RESIDENCIAL'
      }
    ],
    enderecos: [
      {
        logradouro: 'Rua Marrom',
        numero: 9,
        complemento: 'Casa',
        cep: '35689-987',
        cidade: 'Maringá',
        estado: 'Paraná',
        pais: 'Brasil',
        tipo: 'RESIDENCIAL'
      }
    ],
    tipoDeficiencia: 'TDAH',
    certificadoDeficiencia: 'http://certificado.com',
    imagemDocumento: 'http://image.jpg'
  }
]



//--------------FIND BY DATANASCIMENTO < "2003-01-01"--------------
Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.usuario.find({"dataNascimento": {$lt: new Date("2003-01-01")}});
[
  {
    _id: ObjectId("65d34aa199597fc500bac7d8"),
    nome: 'Jorginho',
    dataNascimento: ISODate("2000-01-01T00:00:00.000Z"),
    cpf: '032213654598',
    email: 'jorginho@dominio.com',
    senha: 'jorginho123',
    ehPcd: 'N',
    contatos: [
      {
        descricao: 'Bom dia',
        telefone: '4002-8922',
        tipo: 'COMERCIAL'
      }
    ],
    enderecos: [
      {
        logradouro: 'Rua Cinza',
        numero: 9,
        complemento: 'apto 2',
        cep: '13254-321',
        cidade: 'Pirapozinho',
        estado: 'Sao Paulo',
        pais: 'Brasil',
        tipo: 'RESIDENCIAL'
      },
      {
        logradouro: 'Rua Azul',
        numero: 15,
        cep: '32165-215',
        cidade: 'Osasco',
        estado: 'Sao Paulo',
        pais: 'Brasil',
        tipo: 'COMERCIAL'
      },
      {
        logradouro: 'Rua Verde',
        numero: 17,
        cep: '56897-512',
        cidade: 'Sao José do Rio Preto',
        estado: 'Sao Paulo',
        pais: 'Brasil',
        tipo: 'COMERCIAL'
      }
    ]
  },
  {
    _id: ObjectId("65d34ac199597fc500bac7d9"),
    nome: 'Mateus',
    dataNascimento: ISODate("2001-01-01T00:00:00.000Z"),
    cpf: '032213654597',
    email: 'mateuzinho@dominio.com',
    senha: 'mateuzinho123',
    ehPcd: 'S',
    contatos: [
      {
        descricao: 'Boa noite',
        telefone: '3221-1245',
        tipo: 'RESIDENCIAL'
      },
      {
        descricao: 'Boa tarde',
        telefone: '3222-2223',
        tipo: 'RESIDENCIAL'
      }
    ],
    enderecos: [],
    tipoDeficiencia: 'manco',
    certificadoDeficiencia: 'http://certificado.com',
    imagemDocumento: 'http://image.jpg'
  },
  {
    _id: ObjectId("65d34ad599597fc500bac7da"),
    nome: 'Miguel',
    dataNascimento: ISODate("1980-01-01T00:00:00.000Z"),
    cpf: '32233222123',
    email: 'miguelzinho@dominio.com',
    senha: 'miguelzinho123',
    ehPcd: 'S',
    contatos: [
      {
        descricao: 'Casa do Miguel',
        telefone: '6656-5565',
        tipo: 'RESIDENCIAL'
      }
    ],
    enderecos: [
      {
        logradouro: 'Rua Marrom',
        numero: 9,
        complemento: 'Casa',
        cep: '35689-987',
        cidade: 'Maringá',
        estado: 'Paraná',
        pais: 'Brasil',
        tipo: 'RESIDENCIAL'
      }
    ],
    tipoDeficiencia: 'TDAH',
    certificadoDeficiencia: 'http://certificado.com',
    imagemDocumento: 'http://image.jpg'
  }
]



//--------------FIND BY DATANASCIMENTO > "2000-01-01" E EDPCD = "S"--------------
Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.usuario.find({"ehPcd": {$eq: "S"}, "dataNascimento": {$gt: new Date("2000-01-01")}});
[
  {
    _id: ObjectId("65d34ac199597fc500bac7d9"),
    nome: 'Mateus',
    dataNascimento: ISODate("2001-01-01T00:00:00.000Z"),
    cpf: '032213654597',
    email: 'mateuzinho@dominio.com',
    senha: 'mateuzinho123',
    ehPcd: 'S',
    contatos: [
      {
        descricao: 'Boa noite',
        telefone: '3221-1245',
        tipo: 'RESIDENCIAL'
      },
      {
        descricao: 'Boa tarde',
        telefone: '3222-2223',
        tipo: 'RESIDENCIAL'
      }
    ],
    enderecos: [],
    tipoDeficiencia: 'manco',
    certificadoDeficiencia: 'http://certificado.com',
    imagemDocumento: 'http://image.jpg'
  }
]



//--------------FIND BY DATANASCIMENTO <= "2000-01-01" OU EDPCD = "S"--------------
Atlas atlas-uupjfq-shard-0 [primary] vs_13_equipe_6> db.usuario.find({$or: [{"dataNascimento": {$lte: new Date("2000-01-01")}}, {"ehPcd": {$eq: "S"}}]});
[
  {
    _id: ObjectId("65d34aa199597fc500bac7d8"),
    nome: 'Jorginho',
    dataNascimento: ISODate("2000-01-01T00:00:00.000Z"),
    cpf: '032213654598',
    email: 'jorginho@dominio.com',
    senha: 'jorginho123',
    ehPcd: 'N',
    contatos: [
      {
        descricao: 'Bom dia',
        telefone: '4002-8922',
        tipo: 'COMERCIAL'
      }
    ],
    enderecos: [
      {
        logradouro: 'Rua Cinza',
        numero: 9,
        complemento: 'apto 2',
        cep: '13254-321',
        cidade: 'Pirapozinho',
        estado: 'Sao Paulo',
        pais: 'Brasil',
        tipo: 'RESIDENCIAL'
      },
      {
        logradouro: 'Rua Azul',
        numero: 15,
        cep: '32165-215',
        cidade: 'Osasco',
        estado: 'Sao Paulo',
        pais: 'Brasil',
        tipo: 'COMERCIAL'
      },
      {
        logradouro: 'Rua Verde',
        numero: 17,
        cep: '56897-512',
        cidade: 'Sao José do Rio Preto',
        estado: 'Sao Paulo',
        pais: 'Brasil',
        tipo: 'COMERCIAL'
      }
    ]
  },
  {
    _id: ObjectId("65d34ac199597fc500bac7d9"),
    nome: 'Mateus',
    dataNascimento: ISODate("2001-01-01T00:00:00.000Z"),
    cpf: '032213654597',
    email: 'mateuzinho@dominio.com',
    senha: 'mateuzinho123',
    ehPcd: 'S',
    contatos: [
      {
        descricao: 'Boa noite',
        telefone: '3221-1245',
        tipo: 'RESIDENCIAL'
      },
      {
        descricao: 'Boa tarde',
        telefone: '3222-2223',
        tipo: 'RESIDENCIAL'
      }
    ],
    enderecos: [],
    tipoDeficiencia: 'manco',
    certificadoDeficiencia: 'http://certificado.com',
    imagemDocumento: 'http://image.jpg'
  },
  {
    _id: ObjectId("65d34ad599597fc500bac7da"),
    nome: 'Miguel',
    dataNascimento: ISODate("1980-01-01T00:00:00.000Z"),
    cpf: '32233222123',
    email: 'miguelzinho@dominio.com',
    senha: 'miguelzinho123',
    ehPcd: 'S',
    contatos: [
      {
        descricao: 'Casa do Miguel',
        telefone: '6656-5565',
        tipo: 'RESIDENCIAL'
      }
    ],
    enderecos: [
      {
        logradouro: 'Rua Marrom',
        numero: 9,
        complemento: 'Casa',
        cep: '35689-987',
        cidade: 'Maringá',
        estado: 'Paraná',
        pais: 'Brasil',
        tipo: 'RESIDENCIAL'
      }
    ],
    tipoDeficiencia: 'TDAH',
    certificadoDeficiencia: 'http://certificado.com',
    imagemDocumento: 'http://image.jpg'
  }
]