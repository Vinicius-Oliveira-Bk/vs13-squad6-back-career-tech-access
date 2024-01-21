//package br.com.dbc.vemser.services;
//
//import br.com.dbc.vemser.exceptions.BancoDeDadosException;
//import br.com.dbc.vemser.model.entities.Cliente;
//import br.com.dbc.vemser.repository.ClienteRepository;
//
//import java.util.List;
//
//public class ClienteService {
//    private ClienteRepository clienteRepository;
//
//    public ClienteService() {
//        clienteRepository = new ClienteRepository();
//    }
//
//    public void cadastrar(Cliente cliente, Long idUsuario) {
//        try {
//            Cliente clienteAdicionado = clienteRepository.create(cliente, idUsuario);
//            System.out.println("✅ Cliente Adicionado com sucesso! " + clienteAdicionado);
//        } catch (BancoDeDadosException e) {
//            System.out.println("❌ ERRO: " + e.getMessage());
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("❌ ERRO: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public Cliente listarUm(Long idCliente) {
//        try {
//            return clienteRepository.getById(idCliente);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void listarTodos() {
//        try {
//            List<Cliente> listar = clienteRepository.getAll();
//            listar.forEach(System.out::println);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void atualizar(Long id, Cliente cliente) {
//        try {
//            clienteRepository.update(id, cliente);
//            System.out.println("✅ Cliente Editado com Sucesso");
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void remover(Long id) {
//        try {
//            clienteRepository.delete(id);
//            System.out.println("✅ Cliente Removido com Sucesso");
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean validarCliente(Cliente cliente) {
//        return true;
//    }
//}
