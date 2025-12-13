package Clients;

import java.util.*;


public class ClientRepository {
    private final List<Clients> clients = new ArrayList<>();
    private int nextId = 1;

    public Clients save(Clients client){
        if (client == null) return null;

        if (client.getId() == 0){
            client.setId(nextId++);
            clients.add(client);
            return client;
        } else {
            return clients.stream()
                    .filter(c -> c.getId() == client.getId())
                    .findFirst()
                    .orElse(null);
        }
    }
}
