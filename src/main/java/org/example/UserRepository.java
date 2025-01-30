package org.example;

import org.example.User;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;

@Repository
public class UserRepository {

    private final DynamoDbTable<User> userTable;

    public UserRepository(DynamoDbEnhancedClient enhancedClient) {
        this.userTable = enhancedClient.table("Users", TableSchema.fromBean(User.class));
    }

    public void save(User user) {
        userTable.putItem(user);
    }

    public Optional<User> getUserById(String userId) {
        return Optional.ofNullable(userTable.getItem(r -> r.key(k -> k.partitionValue(userId))));
    }

    public void deleteUser(String userId) {
        userTable.deleteItem(r -> r.key(k -> k.partitionValue(userId)));
    }
}
