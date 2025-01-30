package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@DynamoDbBean
@Data
@NoArgsConstructor
@AllArgsConstructor  // Required for fromBean() method
public class User {

    private String userId;
    private String name;
    private String email;

    @DynamoDbPartitionKey  // Correctly specify the partition key
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Corrected TableSchema definition
    public static final TableSchema<User> TABLE_SCHEMA = TableSchema.fromBean(User.class);

    public static void main(String[] args) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.create();
        DynamoDbTable<User> mappedTable = enhancedClient.table("Users", TABLE_SCHEMA);

        // You can now perform operations like putting, getting, and deleting items
    }
}
