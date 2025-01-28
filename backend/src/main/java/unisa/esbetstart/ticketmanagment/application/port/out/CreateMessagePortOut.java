package unisa.esbetstart.ticketmanagment.application.port.out;

import unisa.esbetstart.ticketmanagment.domain.model.Message;

public interface CreateMessagePortOut {

    void updateMessage(Message message);
    void saveMessage(Message message);
}
