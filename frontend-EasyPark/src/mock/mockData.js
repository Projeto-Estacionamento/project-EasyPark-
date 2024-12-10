const mockData = {
    usuarios: [
      {
        id: 1,
        nome: "João Silva",
        email: "joao.silva@email.com",
        telefone: "(11) 98765-4321",
        cpf: "123.456.789-00",
        endereco: {
          id: 1,
          cidade: "São Paulo",
          estado: "SP",
          cep: "01234-567"
        },
      },
      {
        id: 2,
        nome: "Maria Santos",
        email: "maria.santos@email.com",
        telefone: "(11) 91234-5678",
        cpf: "987.654.321-00",
        endereco: {
          id: 2,
          cidade: "Rio de Janeiro",
          estado: "RJ",
          cep: "20000-000"
        },
        veiculos: [
          {
            id: 2,
            placa: "XYZ9876",
            tipoVeiculo: "MOTO",
            ocupandoVaga: false,
            fabricante: {
              id: 2,
              modelo: "CB 300",
              marca: "Honda",
              ano: 2021
            }
          }
        ]
      }
    ],
    veiculos: [
      {
        id: 1,
        placa: "ABC1234",
        tipoVeiculo: "CARRO",
        ocupandoVaga: true,
        fabricante: {
          id: 1,
          modelo: "Civic",
          marca: "Honda",
          ano: 2020
        }
      }
    ],
    planos: [
      {
        id: 1,
        tipoPlano: "INTEGRAL",
        tipoVeiculo: "CARRO",
        valorPlano: 300.00,
        horarioInicio: "08:00",
        horarioFim: "22:00"
      },
      {
        id: 2,
        tipoPlano: "MANHA",
        tipoVeiculo: "MOTO",
        valorPlano: 150.00,
        horarioInicio: "08:00",
        horarioFim: "12:00"
      }
    ],
  
    assinaturas: [
      {
        id: 1,
        usuario: 1,
        plano: 1,
        dataPagamento: "2024-03-01T10:00:00",
        dataVencimento: "2024-04-01T10:00:00",
        ativo: true
      }
    ],
  
    tickets: [
      {
        id: 1,
        placaVeiculo: "ABC1234",
        horaChegada: "2024-03-15T09:00:00",
        horaSaida: "2024-03-15T17:00:00",
        tipoTicket: "TICKET_AVULSO",
        tipoVeiculo: "CARRO",
        totalHoras: "8:00:00",
        valorTotalPagar: 40.00
      },
      {
        id: 2,
        placaVeiculo: "XYZ9876",
        horaChegada: "2024-03-15T14:00:00",
        horaSaida: null,
        tipoTicket: "TICKET_MENSALISTA",
        tipoVeiculo: "MOTO",
        totalHoras: null,
        valorTotalPagar: null
      }
    ],
  
    configuracaoSistema: {
      id: 1,
      mostrar: true,
      qtdMoto: 50,
      qtdCarro: 100,
      valorHoraMoto: 5.00,
      valorHoraCarro: 8.00,
      valorDiariaCarro: 40.00,
      valorDiariaMoto: 25.00,
      horaMaximaAvulso: 12
    },
  
    acessos: [
      {
        id: 1,
        password: "admin",
        username: "admin",
        email: "admin@easypark.com",
        tipoAcesso: "ADMINISTRADOR"
      },
      {
        id: 2,
        password: "admin",
        username: "caixa1",
        email: "caixa1@easypark.com",
        tipoAcesso: "CAIXA"
      }
    ],
  
    relatorioTickets: [
      {
        id: 1,
        
        horaEntrada: "2024-03-01T08:00:00",
        horaSaida: "2024-03-01T17:00:00",
        tipoVeiculo: "CARRO",
        valorTotalPagar: 72.00
      }
    ]
  };
  
  export default mockData;