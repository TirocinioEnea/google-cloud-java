/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.pubsub;

import com.google.api.gax.bundling.FlowController;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Clock;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Service;
import com.google.pubsub.v1.PubsubMessage;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import org.joda.time.Duration;

/** Users of the {@link Subscriber} must implement this interface to receive messages. */
interface MessageReceiver {
   enum AckReply {
    /** To be used for acking a message. */
    ACK,
    /** To be used for nacking a message. */
    NACK
  }
  /**
   * Called when a message is received by the subscriber.
   *
   * @return A future that signals when a message has been processed.
   */
  ListenableFuture<AckReply> receiveMessage(PubsubMessage message);
}
