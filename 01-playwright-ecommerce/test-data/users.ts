
export const users = {
  // Valid user credentials
  standard: {
    username: 'standard_user',
    password: 'secret_sauce',
  },

  // Locked user credentials
  locked: {
    username: 'locked_out_user',
    password: 'secret_sauce',
  },

  // Invalid user credentials
  invalid: {
    username: 'invalid_user',
    password: 'wrong_password',
  },
} as const;
