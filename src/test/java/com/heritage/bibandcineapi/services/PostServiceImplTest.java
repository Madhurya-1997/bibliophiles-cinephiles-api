package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository mockPostRepository;
    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private PostServiceImpl postServiceImplUnderTest;

}
