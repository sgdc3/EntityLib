package de.tr7zw.entitylib;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.event.Listener;

public class EntityListener implements Listener {

    public EntityListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(EntityLib.instance,
                ListenerPriority.NORMAL,
                PacketType.Play.Server.SPAWN_ENTITY_LIVING) {
            @Override
            public void onPacketSending(PacketEvent event) {
                try {
                    PacketContainer packet = event.getPacket();
                    Integer entityId = packet.getIntegers().read(0);
                    if (EntityLib.MAPPING.containsKey(event.getPlayer().getWorld().getName() + "_" + entityId)) {
                        packet.getIntegers().write(1, EntityLib.MAPPING.get(event.getPlayer().getWorld().getName() + "_" + entityId));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
