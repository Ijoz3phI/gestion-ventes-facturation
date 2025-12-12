package Clients;

import java.util.*;


public class ClientRepository {
    private final List<Client> clients = new ArrayList<>();
    private int nextId = 1;
    //hna zdt array list fin istocka haadchi
    public Clients save (Clients clients){
        if (clients.getId()==0){
            clients.setId(nextId++);
            clients.add(clients);
            return clients;
        }else {
            return clients.stream()
                    .filter(c -> c.getId() == client.getId())
                    .findFirst()
                    .orElse(null);
        }
    }
}
