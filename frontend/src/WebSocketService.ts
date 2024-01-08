// src/WebSocketService.ts
import SockJS from 'sockjs-client';
import Stomp, { Client, Frame } from 'webstomp-client';

class WebSocketService {
  private stompClient: Client | null = null;

  connect(channelId: string, onMessageReceived: (msg: any) => void): void {
    const socket = new SockJS('http://localhost:8080/ws'); // Adjust with your server URL
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, () => {
      this.stompClient?.subscribe(`/topic/channel/${channelId}`, (msg: Frame) => {
        onMessageReceived(JSON.parse(msg.body));
      });
    }, this.onError);
  }

  sendMessage(channelId: string, message: string): void {
    if (this.stompClient && this.stompClient.connected) {
      const msg = { content: message }; // Adjust the message format as per your backend
      this.stompClient.send(`/app/chat.sendMessage/${channelId}`, JSON.stringify(msg), {});
    }
  }

  disconnect(): void {
    if (this.stompClient) {
      this.stompClient.disconnect();
    }
  }

  private onError(error: string): void {
    console.error('WebSocket connection error: ', error);
  }
}

export default new WebSocketService();
