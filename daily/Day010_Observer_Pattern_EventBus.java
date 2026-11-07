/**
 * Daily Java Challenge - Observer Pattern
 * Topic: Event-driven Architecture & Behavioral Design Patterns
 */
import java.util.*;

public class Day010_Observer_Pattern_EventBus {

    interface EventListener {
        void onEvent(String eventType, String message);
    }

    static class EventManager {
        private final Map<String, List<EventListener>> listeners = new HashMap<>();

        public void subscribe(String eventType, EventListener listener) {
            listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
        }

        public void notify(String eventType, String message) {
            List<EventListener> users = listeners.getOrDefault(eventType, Collections.emptyList());
            for (EventListener listener : users) {
                listener.onEvent(eventType, message);
            }
        }
    }

    public static void main(String[] args) {
        EventManager events = new EventManager();

        events.subscribe("ORDER_PLACED", (type, msg) -> System.out.println("[EmailService] " + msg));
        events.subscribe("ORDER_PLACED", (type, msg) -> System.out.println("[SMSNotification] " + msg));
        events.subscribe("PAYMENT_FAILED", (type, msg) -> System.out.println("[AlertCenter] " + msg));

        System.out.println("Broadcasting ORDER_PLACED:");
        events.notify("ORDER_PLACED", "Order #9871 has been confirmed.");

        System.out.println("\nBroadcasting PAYMENT_FAILED:");
        events.notify("PAYMENT_FAILED", "Card declined for Order #9872.");
    }
}
