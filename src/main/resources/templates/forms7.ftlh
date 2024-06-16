<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Rasmiy</title>
    <link
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
            rel="stylesheet"
    />
    <link rel="stylesheet" href="../../static/css/tailwind.output.css"/>
    <script
            src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
            defer
    ></script>
    <script src="../../static/js/init-alpine.js"></script>
    <!-- You need focus-trap.js to make the modal accessible -->
    <script src="../../static/js/focus-trap.js" defer></script>
    <!-- Flatpickr CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

    <!-- Flatpickr JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

</head>
<body>
<style>
    #formB {
        margin-bottom: 30px;
    }

    #modal {
        margin-bottom: 30px;
    }
</style>
<div
        class="flex h-screen bg-gray-50 dark:bg-gray-900"
        :class="{ 'overflow-hidden': isSideMenuOpen}"
>
    <!-- Desktop sidebar -->
    <aside
            class="z-20 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block flex-shrink-0"
    >
        <div class="py-4 text-gray-500 dark:text-gray-400">
            <a
                    class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200"
                    href="#"
            >
                Rasmiy
            </a>
            <ul class="mt-6">
                <li class="relative px-6 py-3">
              <span
                      class="absolute inset-y-0 left-0 w-1 bg-purple-600 rounded-tr-lg rounded-br-lg"
                      aria-hidden="true"
              ></span>
                    <a
                            class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100"
                            href="/dashboard"
                    >
                        <svg
                                class="w-5 h-5"
                                aria-hidden="true"
                                fill="none"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                            ></path>
                        </svg>
                        <span class="ml-4"> <@spring.message 'documents'/></span>
                    </a>
                </li>
            </ul>
            <ul>

                <li class="relative px-6 py-3">
                    <button
                            class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            @click="togglePagesMenu"
                            aria-haspopup="true"
                    >
                <span class="inline-flex items-center">
                  <svg
                          class="w-5 h-5"
                          aria-hidden="true"
                          fill="none"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          viewBox="0 0 24 24"
                          stroke="currentColor"
                  >
                    <path
                            d="M4 5a1 1 0 011-1h14a1 1 0 011 1v2a1 1 0 01-1 1H5a1 1 0 01-1-1V5zM4 13a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6zM16 13a1 1 0 011-1h2a1 1 0 011 1v6a1 1 0 01-1 1h-2a1 1 0 01-1-1v-6z"
                    ></path>
                  </svg>
                   <a href="/logout"> <span class="ml-4"><@spring.message 'logout'/></span></a>
                </span>

                    </button>

                </li>
            </ul>
        </div>
    </aside>

    <!-- Mobile sidebar -->
    <!-- Backdrop -->
    <div
            x-show="isSideMenuOpen"
            class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"
    ></div>
    <aside
            class="fixed inset-y-0 z-20 flex-shrink-0 w-64 mt-16 overflow-y-auto bg-white dark:bg-gray-800 md:hidden"
            x-show="isSideMenuOpen"

            @click.away="closeSideMenu"
            @keydown.escape="closeSideMenu"
    >
        <div class="py-4 text-gray-500 dark:text-gray-400">
            <a
                    class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200"
                    href="#"
            >
                Rasmiy
            </a>
            <ul class="mt-6">
                <li class="relative px-6 py-3">
              <span
                      class="absolute inset-y-0 left-0 w-1 bg-purple-600 rounded-tr-lg rounded-br-lg"
                      aria-hidden="true"
              ></span>
                    <a
                            class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100"
                            href="/dashboard"
                    >
                        <svg
                                class="w-5 h-5"
                                aria-hidden="true"
                                fill="none"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                            ></path>
                        </svg>
                        <span class="ml-4"> <@spring.message 'documents'/></span>
                    </a>
                </li>
            </ul>
            <ul>
                <li class="relative px-6 py-3">
                    <button
                            class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            @click="togglePagesMenu"
                            aria-haspopup="true"
                    >
                <span class="inline-flex items-center">
                  <svg
                          class="w-5 h-5"
                          aria-hidden="true"
                          fill="none"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          viewBox="0 0 24 24"
                          stroke="currentColor"
                  >
                    <path
                            d="M4 5a1 1 0 011-1h14a1 1 0 011 1v2a1 1 0 01-1 1H5a1 1 0 01-1-1V5zM4 13a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6zM16 13a1 1 0 011-1h2a1 1 0 011 1v6a1 1 0 01-1 1h-2a1 1 0 01-1-1v-6z"
                    ></path>
                  </svg>
                   <a href="/logout"> <span class="ml-4"><@spring.message 'logout'/></span></a>
                </span>

                    </button>

                </li>
            </ul>

        </div>
    </aside>
    <div class="flex flex-col flex-1">
        <header class="z-10 py-4 bg-white shadow-md dark:bg-gray-800">
            <div
                    class="container flex items-center justify-between h-full px-6 mx-auto text-purple-600 dark:text-purple-300"
            >
                <!-- Mobile hamburger -->
                <button
                        class="p-1 -ml-1 mr-5 rounded-md md:hidden focus:outline-none focus:shadow-outline-purple"
                        @click="toggleSideMenu"
                        aria-label="Menu"
                >
                    <svg
                            class="w-6 h-6"
                            aria-hidden="true"
                            fill="currentColor"
                            viewBox="0 0 20 20"
                    >
                        <path
                                fill-rule="evenodd"
                                d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
                                clip-rule="evenodd"
                        ></path>
                    </svg>
                </button>
                <!-- Search input -->
                <div class="flex justify-center flex-1 lg:mr-32">
                </div>
                <ul class="flex items-center flex-shrink-0 space-x-6">
                    <!-- Theme toggler -->
                    <li class="flex">
                        <button
                                class="rounded-md focus:outline-none focus:shadow-outline-purple"
                                @click="toggleTheme"
                                aria-label="Toggle color mode"
                        >
                            <template x-if="!dark">
                                <svg
                                        class="w-5 h-5"
                                        aria-hidden="true"
                                        fill="currentColor"
                                        viewBox="0 0 20 20"
                                >
                                    <path
                                            d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"
                                    ></path>
                                </svg>
                            </template>
                            <template x-if="dark">
                                <svg
                                        class="w-5 h-5"
                                        aria-hidden="true"
                                        fill="currentColor"
                                        viewBox="0 0 20 20"
                                >
                                    <path
                                            fill-rule="evenodd"
                                            d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z"
                                            clip-rule="evenodd"
                                    ></path>
                                </svg>
                            </template>
                        </button>
                    </li>
                    <!-- Notifications menu -->
                    <li class="relative">
                        <button
                                class="relative align-middle rounded-md focus:outline-none focus:shadow-outline-purple"
                                @click="toggleNotificationsMenu"
                                @keydown.escape="closeNotificationsMenu"
                                aria-label="Notifications"
                                aria-haspopup="true"
                        >
                            <svg
                                    class="w-5 h-5"
                                    aria-hidden="true"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                            >
                                <path
                                        d="M10 2a6 6 0 00-6 6v3.586l-.707.707A1 1 0 004 14h12a1 1 0 00.707-1.707L16 11.586V8a6 6 0 00-6-6zM10 18a3 3 0 01-3-3h6a3 3 0 01-3 3z"
                                ></path>
                            </svg>
                            <!-- Notification badge -->
                            <span
                                    aria-hidden="true"
                                    class="absolute top-0 right-0 inline-block w-3 h-3 transform translate-x-1 -translate-y-1 bg-red-600 border-2 border-white rounded-full dark:border-gray-800"
                            ></span>
                        </button>
                        <template x-if="isNotificationsMenuOpen">
                        </template>
                    </li>

                    <li class="flex">
                        <div class="relative" x-data="{ isOpen: false, language: 'kg' }">
                            <button @click="isOpen = !isOpen"
                                    class="rounded-md flex items-center focus:outline-none focus:shadow-outline-purple"
                                    aria-label="Toggle Language Menu">
                                <!-- Language text -->
                                <span class="mx-2"><@spring.message 'lang'/></span>
                            </button>
                            <!-- Language dropdown menu -->
                            <ul x-show="isOpen" @click.away="isOpen = false"
                                class="absolute right-0 w-28 py-1 mt-2 bg-white border border-gray-200 rounded-md shadow-lg">
                                <li>
                                    <a @click="setLanguage('kg')" href="?lang=en"
                                       class="block px-4 py-2 text-sm text-gray-700 text-center"
                                       x-bind:class="{ 'bg-gray-100': language === 'kg' }">Кыргызча</a>
                                </li>
                                <li>
                                    <a @click="setLanguage('ru')" href="?lang=ru"
                                       class="block px-4 py-2 text-sm text-gray-700 text-center"
                                       x-bind:class="{ 'bg-gray-100': language === 'ru' }">Русский</a>
                                </li>
                            </ul>
                        </div>
                    </li>

                </ul>
            </div>
        </header>
        <main class="h-full pb-16 overflow-y-auto">

            <!-- End of modal backdrop -->
            <div class="container px-6 mx-auto grid">
                <h2
                        class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
                >
                    <@spring.message 'academicLeaveStatement'/>
                </h2>
                <button
                        id="modal"
                        @click="openModal"
                        class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                >
                    <@spring.message 'seeTemplate'/>
                </button>
                <div
                        x-show="isModalOpen"
                        class="fixed inset-0 z-30 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"
                >
                    <!-- Modal -->
                    <div
                            x-show="isModalOpen"
                            @click.away="closeModal"
                            @keydown.escape="closeModal"
                            class="w-full px-6 py-4 overflow-hidden bg-white rounded-t-lg dark:bg-gray-800 sm:rounded-lg sm:m-4 sm:max-w-xl"
                            role="dialog"
                            id="modal"
                    >
                        <!-- Remove header if you don't want a close icon. Use modal body to place modal tile. -->
                        <header class="flex justify-end">
                            <button
                                    class="inline-flex items-center justify-center w-6 h-6 text-gray-400 transition-colors duration-150 rounded dark:hover:text-gray-200 hover: hover:text-gray-700"
                                    aria-label="close"
                                    @click="closeModal"
                            >
                                <svg
                                        class="w-4 h-4"
                                        fill="currentColor"
                                        viewBox="0 0 20 20"
                                        role="img"
                                        aria-hidden="true"
                                >
                                    <path
                                            d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                                            clip-rule="evenodd"
                                            fill-rule="evenodd"
                                    ></path>
                                </svg>
                            </button>
                        </header>

                        <img src="../../static/img/extension.png" alt="Image description" class="mt-4 w-full rounded-lg">
                        <footer
                                class="flex flex-col items-center justify-end px-6 py-3 -mx-6 -mb-4 space-y-4 sm:space-y-0 sm:space-x-6 sm:flex-row bg-gray-50 dark:bg-gray-800"
                        >
                            <button
                                    @click="closeModal"
                                    class="w-full px-5 py-3 text-sm font-medium leading-5 text-white text-gray-700 transition-colors duration-150 border border-gray-300 rounded-lg dark:text-gray-400 sm:px-4 sm:py-2 sm:w-auto active:bg-transparent hover:border-gray-500 focus:border-gray-500 active:text-gray-500 focus:outline-none focus:shadow-outline-gray"
                            >
                                <@spring.message 'close'/>
                            </button>
                        </footer>
                    </div>
                </div>


                <!-- General elements -->
                <h4
                        class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
                >
                    <@spring.message 'genDocument'/>
                </h4>
                <form action="/form/education/academicLeave" method="post">
                    <div
                            class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
                    >
                        <label class="block text-sm" id="formB">
    <span class="text-gray-700 dark:text-gray-400">
        <@spring.message 'formF'/>
    </span>
                            <input
                                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input<#if fieldErrors?has_content && fieldErrors["F"]??> border-red-600</#if>"
                                    placeholder="Кайратов"
                                    name="F"
                            />
                            <#if fieldErrors?has_content && fieldErrors["F"]??>
                                <!-- Display the error message(s) for the 'F' field -->
                                <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["F"]}
        </span><br>
                            </#if>
                        </label>


                        <label class="block text-sm" id="formB">
                            <span class="text-gray-700 dark:text-gray-400">  <@spring.message 'formI'/></span>
                            <input
                                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                    placeholder="Адыл"
                                    name="I"
                            />
                            <#if fieldErrors?has_content && fieldErrors["I"]??>
                                <!-- Display the error message(s) for the 'F' field -->
                                <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["I"]}
        </span><br>
                            </#if>
                        </label>
                        <label class="block text-sm" id="formB">
                            <span class="text-gray-700 dark:text-gray-400">  <@spring.message 'formO'/></span>
                            <input
                                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                    placeholder="Асанович"
                                    name="O"
                            />
                            <#if fieldErrors?has_content && fieldErrors["O"]??>
                                <!-- Display the error message(s) for the 'F' field -->
                                <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["O"]}
        </span><br>
                            </#if>
                        </label>

                        <div class="mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                  <@spring.message 'formCourse'/>
                </span>
                            <div class="mt-4 text-sm">
                                <span class="text-gray-700 dark:text-gray-400">Курс</span>
                                <div class="mt-2" id="formB">
                                    <label class="inline-flex items-center text-gray-600 dark:text-gray-400">
                                        <input type="radio" name="course"
                                               class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                               value="1"/>
                                        <span class="ml-2">1</span>
                                    </label>
                                    <label class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400">
                                        <input type="radio" name="course"
                                               class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                               value="2"/>
                                        <span class="ml-2">2</span>
                                    </label>
                                    <label class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400">
                                        <input type="radio" name="course"
                                               class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                               value="3"/>
                                        <span class="ml-2">3</span>
                                    </label>
                                    <label class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400">
                                        <input type="radio" name="course"
                                               class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                               value="4"/>
                                        <span class="ml-2">4</span>
                                    </label>
                                    <label class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400">
                                        <input type="radio" name="course"
                                               class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                               value="5"/>
                                        <span class="ml-2">5</span>
                                    </label>
                                </div>
                            </div>
                            <#if fieldErrors?has_content && fieldErrors["course"]??>
                                <!-- Display the error message(s) for the 'F' field -->
                                <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["course"]}
        </span><br>
                            </#if>

                            <label class="block mt-4 text-sm" id="formB">
                <span class="text-gray-700 dark:text-gray-400">
                 <@spring.message 'formUniversity'/>
                </span>
                                <select
                                        name="universityId"
                                        class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                >
                                    <#list universities as university>
                                        <#if lang=="en">
                                            <option value="${university.getId()}">${university.universityNameKg}</option>
                                        <#else >
                                            <option value="${university.getId()}">${university.universityNameRu}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </label>

                            <label class="block text-sm" id="formB">
                                <span class="text-gray-700 dark:text-gray-400"><@spring.message 'formRectorF'/></span>
                                <input
                                        name="rectorF"
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="Кылычбекова"
                                />
                                <#if fieldErrors?has_content && fieldErrors["rectorF"]??>
                                    <!-- Display the error message(s) for the 'F' field -->
                                    <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["rectorF"]}
        </span><br>
                                </#if>
                            </label>
                            <label class="block text-sm" id="formB">
                                <span class="text-gray-700 dark:text-gray-400"><@spring.message 'formRectorI'/></span>
                                <input
                                        name="rectorI"
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="Асан"
                                />
                                <#if fieldErrors?has_content && fieldErrors["rectorI"]??>
                                    <!-- Display the error message(s) for the 'F' field -->
                                    <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["rectorI"]}
        </span><br>
                                </#if>
                            </label>

                            <label class="block text-sm" id="formB">
                                <span class="text-gray-700 dark:text-gray-400"><@spring.message 'formRectorO'/></span>
                                <input
                                        name="rectorO"
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="Жайылович"
                                />
                                <#if fieldErrors?has_content && fieldErrors["rectorO"]??>
                                    <!-- Display the error message(s) for the 'F' field -->
                                    <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["rectorO"]}
        </span><br>
                                </#if>
                            </label>

                            <label class="block text-sm" id="formB">
                                <span class="text-gray-700 dark:text-gray-400">Группа</span>
                                <input
                                        name="group"
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="Ивт(ки)-1-20"
                                />
                                <#if fieldErrors?has_content && fieldErrors["group"]??>
                                    <!-- Display the error message(s) for the 'F' field -->
                                    <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["group"]}
        </span><br>
                                </#if>
                            </label>

                            <label class="block text-sm" id="formB">
                                <span class="text-gray-700 dark:text-gray-400">    <@spring.message 'formFaculty'/></span>
                                <input
                                        name="faculty"
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="МТФ"
                                />
                                <#if fieldErrors?has_content && fieldErrors["faculty"]??>
                                    <!-- Display the error message(s) for the 'F' field -->
                                    <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["faculty"]}
        </span><br>
                                </#if>
                            </label>


                            <label class="block mt-4 text-sm" id="formB">
                <span class="text-gray-700 dark:text-gray-400">
                  <@spring.message 'formAcademicReason'/>
                </span>
                                <select
                                        name="reason"
                                        class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                >
                                    <#list reasons as reason>
                                        <#if lang=="en">
                                            <option value="${reason.affixedText}">${reason.academicReasonRu}</option>
                                        <#else >
                                            <option value="${reason.affixedText}">${reason.academicReasonKg}</option>
                                        </#if>

                                    </#list>
                                </select>
                            </label>
                            <label class="block text-sm" id="formB">
                                <span class="text-gray-700 dark:text-gray-400"><@spring.message 'selectExtension'/></span>
                                <input
                                        id="datePickerField"
                                        name="extensionDate"
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="20.05.02"
                                />
                                <#if fieldErrors?has_content && fieldErrors["extensionDate"]??>
                                    <!-- Display the error message(s) for the 'extensionDate' field -->
                                    <span class="error-message text-xs text-red-600 dark:text-red-400">
            ${fieldErrors["extensionDate"]}
        </span><br>
                                </#if>
                            </label>

                        </div>
                        <button type="submit"
                                class="bg-purple-600 hover:bg-purple-800 text-white font-bold py-2 px-4 rounded">
                            Генерация
                        </button>
                    </div>
                </form>
            </div>

        </main>
    </div>
</div>
<script>
    // Initialize Flatpickr
    flatpickr("#datePickerField", {
        dateFormat: "Y-m-d", // Format the date as YYYY-MM-DD
        enableTime: false, // Disable time selection
        altInput: true, // Enable a secondary input field for better UX
        altFormat: "F j, Y", // Format the secondary input field's date (e.g., January 1, 2024)
        locale: {
            // Define locale settings (optional)
            firstDayOfWeek: 1, // Start the week on Monday
        },
    });
</script>

</body>
</html>
