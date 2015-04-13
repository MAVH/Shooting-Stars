SELECT chatId, chat.user1Id, user_name, surname  
            FROM user_info JOIN chat ON user_info.userId = chat.user1Id 
            WHERE user2Id =2  
            UNION  
            SELECT chatId, chat.user2Id, user_name, surname  
            FROM user_info JOIN chat ON user_info.userId = chat.user2Id  
            WHERE user1Id = 2 LIMIT 0,1